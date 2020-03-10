#!/bin/sh

code=$1
answer=$2
cd Labs
cd ${code:0:4}

for i in `ls`
do
	cd $i
	if gcc 2>/dev/null ${code}.c -o temp
	then
		output="$(./temp)"
		if [ $output = $answer ]
		then
			echo “${i}';'3”
		else
			echo “${i}';'2”

		fi

	else
		echo “${i}';'1”
	fi
	cd ..
done		
