#!/usr/bin/python3
# GitHub latest release package retrieval
import json
import requests
from argparse import ArgumentParser

def parse_arguments():
    parser = ArgumentParser(
        prog="retrieve_latest_release",
        description="Retrieves latest release from a GitHub repository",
        epilog="GitHub user and repository name are mandatory, as well as the file pattern (.deb, .tar.gz, etc...)"
    )

    parser.add_argument('--gh-user', dest='gh_user', type=str, help='The GitHub username', required=True)
    parser.add_argument('--gh-repo', dest='gh_repo', help='The GitHub repository', required=True)
    parser.add_argument('--pattern', dest='pattern', help='The pattern of the asst to download', default='.deb', required=False)

    return parser.parse_args()


def main():
    args = parse_arguments()
    print(f"Requesting latest release from {args.gh_user}/{args.gh_repo}, ({args.pattern} format)")
    gh_api_url=f"https://api.github.com/repos/{args.gh_user}/{args.gh_repo}/releases/latest"
    r = requests.get(gh_api_url)
    response_text = json.loads(r.content)
    #print(json.dumps(response_text, indent=2))

    # Search fot the first asset that matches the file pattern
    if "assets" in response_text:
        for asset in response_text["assets"]:
            if "name" in asset and asset["name"].endswith(args.pattern):
                print(f'Downloading {asset["browser_download_url"]}...')
                filename = asset["name"]
                download = requests.get(asset["browser_download_url"])
                print(f"Saving content into {filename}")
                open(filename, "wb").write(download.content)
                break

if __name__ == '__main__':
    # Execute the main program
    main()