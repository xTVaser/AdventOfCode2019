$client = New-Object System.Net.WebClient

$dateStr = Read-Host 'What is the date in the Advent Calendar'
[int]$date = [convert]::ToInt32($dateStr, 10)

If ($date -gt 25 -Or $date -lt 1) {
    Write-Host "Invalid Date, 1-25 pls"
    Exit
}

$cookie = Read-Host 'Enter Session Cookie for www.adventofcode.com'
$client.Headers.Add([System.Net.HttpRequestHeader]::Cookie, "session=" + $cookie)

$client.DownloadFile("http://adventofcode.com/2019/day/$date/input", 'input')

New-Item -Path "." -Name "workdir" -ItemType "directory"
Move-Item "input" -Destination "workdir/"

# TODO - day description into README https://ridicurious.com/2017/01/24/powershell-tip-parsing-html-from-a-local-file-or-a-string/
# TODO support setting up multiple languages