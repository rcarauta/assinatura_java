#!/bin/bash

mapfile -d $'\0' files < <(find ./ -type f -name \*.java)

comentarCodigo="false"
temHead="false"
branchs=""
projeto=""
mensagem=""
versaoAntiga=""
versaoNova=""
gradlePath=""
mensagemGradle=""
conteudoLine=""
comandDelete="d"
count=0

read_file() {
	#read files in java
	for file in $files; do
		while read line; do
			count=$((count+1))
		done < $file
	done
	
	echo "Total Lines: $count"
}

################################ main ###################################

read_file

