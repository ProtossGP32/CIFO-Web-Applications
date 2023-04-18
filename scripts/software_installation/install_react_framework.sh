#!/bin/bash
#   CIFO Web Applications - Software installation
#   - This script install the required software for the course
#   - Categories:
#       - IDEs
#       - Version Control Sofware
#       - Documentation

NODE_VERSION=0

while getopts n: flag
do
	case "${flag}" in
		n) NODE_VERSION=${OPTARG};;
	esac
done

# Define the installation command
# -qq flag prior to the install command reduces the output messages except for errors
# -y after the install command is for non-interactive/unattended installations
INSTALL="apt-get -qq install -y"
UPDATE="apt-get -qq update"

# Pre-requisites:
# - Install download and GPG binaries
# - Apt installation via HTTPS paths must also be installed
#$INSTALL wget gpg curl apt-transport-https

# NVM installation:
# =================
if [ -x "$(command -v nvm)" ]; then
	echo "Installing NVM..."
	# Download and install NVM
	curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.39.3/install.sh | bash
	# Execute this if NVM is not available after installation script
	export NVM_DIR="${HOME}/.nvm"
	[ -s "$NVM_DIR/nvm.sh" ] && \. "$NVM_DIR/nvm.sh" # This loads nvm
	[ -s "${NVM_DIR}/bash_completion" ] && \. "${NVM_DIR}/bash_completion" # This loads nvm bash_completion

fi

# Node.js installation
# ====================
if [[ ${NODE_VERSION} -ne 0 ]]; then
	# Execute this if NVM is not available after installation script
	export NVM_DIR="${HOME}/.nvm"
	[ -s "$NVM_DIR/nvm.sh" ] && \. "$NVM_DIR/nvm.sh" # This loads nvm
	[ -s "${NVM_DIR}/bash_completion" ] && \. "${NVM_DIR}/bash_completion" # This loads nvm bash_completion
	# Install the desired version
	echo "Node.js version selected: ${NODE_VERSION}"
	nvm install ${NODE_VERSION}
	# Use the installed version
	nvm use ${NODE_VERSION}
	# Check the version
	node -v
	# Install npx
	npm install npx
fi

# (Do not use this if installing NVM)
# ===================================
#if [[ ${NODE_VERSION} -ne 0 ]]; then
#	echo "Node.js version selected: ${NODE_VERSION}"
#
#	# Add Node.js PPA to the system
#	curl -sL https://deb.nodesource.com/setup_18.x | sudo -E bash -
#
#	# Update repositories
#	# ===================
#	$UPDATE
#
#	# Install packages
#	# ================
#
#	# Node.js
#	$INSTALL nodejs
#fi
