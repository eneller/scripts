#!/bin/bash
BASE_URL="https://mymuesli.com/"
STRING_ALPHABET="abcdefghijklmnopqrstuvwxyz123456789"
STRING_LENGTH="6"
STRING="aaaaaa"
for i in 1 2 ; do
  curl ${BASE_URL}$STRING | grep "Einlösbar bis" && echo $output
done


incrementString()
{
  inputPos=$1;
  if [ $# -eq 0 ]; then
    inputPos=$STRING_LENGTH - 1
  fi
  # TODO find alphabetic pos of last String char, increment 
  if [ ]; then # if is highest number, loop around, repeat for higher value
    newInputPos=$inputPos -1
    incrementString ${inputPos}-1
  fi

}

incrementChar()
{
  $char="$1"
  return $char
}
