#!/bin/bash
#   CIFO Web Applications - Software installation
#   - This script install the required software for the course
#   - Categories:
#       - IDEs
#       - Version Control Sofware
#       - Documentation

# Check that the user has enough privileges to install software
if [[ $EUID -ne 0 ]]; then
    echo "This script must be run with administrator privileges. Please execute it with 'sudo' or as root user"
    exit 1
fi

# Define the installation command
# -qq flag prior to the install command reduces the output messages except for errors
# -y after the install command is for non-interactive/unattended installations
INSTALL="apt-get -qq install -y"
UPDATE="apt-get -qq update"

# Pre-requisites:
# - Install download and GPG binaries
# - Apt installation via HTTPS paths must also be installed
#sudo apt-get install -y wget gpg curl apt-transport-https
$INSTALL wget gpg curl apt-transport-https

# Additional repositories installation
# ====================================
# IDEs:
echo "Configuring repositories for IDEs..."
echo "===================================="
# - Microsoft: Visual Studio Code
echo "- Visual Studio Code"
# Retrieve the Microsoft GPG key and install it
wget -qO- https://packages.microsoft.com/keys/microsoft.asc | gpg --dearmor > packages.microsoft.gpg
install -D -o root -g root -m 644 packages.microsoft.gpg /etc/apt/keyrings/packages.microsoft.gpg
# Add the Microsoft Visual Studio Code repository to the apt sources list
sh -c 'echo "deb [arch=amd64,arm64,armhf signed-by=/etc/apt/keyrings/packages.microsoft.gpg] https://packages.microsoft.com/repos/code stable main" > /etc/apt/sources.list.d/vscode.list'
# Delete the already installed GPG key
rm -f packages.microsoft.gpg
echo ""
# - Sublime Text
echo "- Sublime Text"
# Retrieve and install GPG key
wget -qO - https://download.sublimetext.com/sublimehq-pub.gpg | gpg --dearmor > sublimehq-pub.gpg
install -D -o root -g root -m 644 sublimehq-pub.gpg /usr/share/keyrings/sublimehq-pub.gpg
# Add Sublime Text repository to apt sources list
echo "deb [arch=$(dpkg --print-architecture) signed-by=/usr/share/keyrings/sublimehq-pub.gpg] https://download.sublimetext.com/ apt/stable/" | tee /etc/apt/sources.list.d/sublime-text.list > /dev/null
# Delete the already installed GPG key
rm -f sublimehq-pub.gpg
echo ""
# VCS:
echo "Configuring repositories for VCS:"
echo "================================="
# - GitHub:
echo "- GitHub"
# Install CURL to retrieve GitHub GPG key
#type -p curl >/dev/null || sudo apt install curl -y
# Retrieve the GPG key and install it
curl -fsSL https://cli.github.com/packages/githubcli-archive-keyring.gpg | dd of=/usr/share/keyrings/githubcli-archive-keyring.gpg
chmod go+r /usr/share/keyrings/githubcli-archive-keyring.gpg
# Add GitHub CLI repository to apt sources list
echo "deb [arch=$(dpkg --print-architecture) signed-by=/usr/share/keyrings/githubcli-archive-keyring.gpg] https://cli.github.com/packages stable main" | tee /etc/apt/sources.list.d/github-cli.list > /dev/null
echo ""
# Browsers:
echo "Configuring repositories for browsers:"
echo "======================================"
# - Google Chrome
echo "- Google Chrome"
# Retrieve and install the public key
wget -q -O - https://dl.google.com/linux/linux_signing_key.pub | gpg --dearmor > google-chrome-pub.gpg
install -D -o root -g root -m 644 google-chrome-pub.gpg /usr/share/keyrings/google-chrome-pub.gpg
# Add Google Chrome repository to apt sources list
echo "deb [arch=$(dpkg --print-architecture) signed-by=/usr/share/keyrings/google-chrome-pub.gpg] http://dl.google.com/linux/chrome/deb/ stable main" | tee /etc/apt/sources.list.d/google-chrome.list > /dev/null
# Delete the already installed GPG key
rm -f google-chrome-pub.gpg
echo ""
# Repositories update
echo "Updating repositories packages lists..."
echo "======================================="
$UPDATE
echo "Done"
echo ""
# Software installation
echo "Installing software from repositories:"
echo "======================================"
# - IDEs: Visual Studio Code, vim, Sublime Text
echo "Installing IDEs..."
$INSTALL code vim sublime-text
echo ""
# - VCS: Git, GitHub
echo "Installing VCS..."
$INSTALL git gh
echo ""
# - Browsers: Google Chrome (stable)
echo "Installing browsers..."
$INSTALL google-chrome-stable
echo ""
# - Documentation: TBD
echo "Installing Documentation software... TBD"
echo ""
# - Desktop tools
echo "Installing Desktop tools..."
$INSTALL flameshot
echo ""

# External software
echo "Installing software not in repositories:"
echo "========================================"
# Jetbrains Toolbox: once installed, use it to install IntelliJ IDEA
echo "- Jetbrains Toolbox"
if ! [ $(command -v jetbrains-toolbox) ] && [ -f tools/jetbrains-toolbox.sh ]; then
    # Install toolbox as ROOT and execute it as the current user
    # This is required as jetbrains toolbox configures the installation paths based on the 'home' folder of the user that executes it
    ./tools/jetbrains-toolbox.sh && su -c jetbrains-toolbox $SUDO_USER
    echo "Toolbox installed, now install IntelliJ IDEA from there"
else
    echo "WARNING: Either jetbrains-toolbox.sh script not found or already installed. Skipping installation"
fi
echo ""

# Install from latest releases of GitHub repositories
echo "Install from GitHub repositories latest releases"
echo "================================================"
if [[ -f tools/retrieve_latest_release.py ]]; then
    # VCS:
    # - GitHub Desktop
    echo "- GitHub Desktop"
    ./tools/retrieve_latest_release.py --gh-user shiftkey --gh-repo desktop --pattern .deb
    echo ""
    # Documentation:
    # - Obsidian
    echo "- Obsidian"
    ./tools/retrieve_latest_release.py --gh-user obsidianmd --gh-repo obsidian-releases --pattern .deb
    echo ""
    # - Quarto
    echo "- Quarto"
    ./tools/retrieve_latest_release.py --gh-user quarto-dev --gh-repo quarto-cli --pattern .deb
    echo ""
    echo "Installing all downloaded packages..."
    $INSTALL ./*.deb
    echo "Deleting already installed packages..."
    rm -r *.deb
else
    echo "ERROR: retrieve_latest_release.py script not found!! Aborting installation"
fi

echo "DONE!!"
