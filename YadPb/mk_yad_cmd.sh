#!/usr/bin/bash
# Runs the yad --help-all command line and captures the output to a file.
#
if [ "$#" -eq 1 ]; then
	$1 --help-all > ~/YadPb/yad_cmd.txt
else
	echo "No YAD program found with the which program.\nNeed to manually set yad program in Settings screen." > ~/YadPb/yad_cmd.txt
fi
