#!/bin/bash
base_url="https://www.mymuesli.com/"
string_alphabet=`echo {a..z} {0..9}`
curl_options="--connect-timeout 1 --silent"
grep_options="--quiet"
for c1 in $string_alphabet; do
for c2 in $string_alphabet; do
for c3 in $string_alphabet; do
for c4 in $string_alphabet; do
for c5 in $string_alphabet; do
for c6 in $string_alphabet; do
  curl $curl_options ${base_url}$c1$c2$c3$c4$c5$c6 | rg $grep_options "Nicht gefunden"  
  if [ ${PIPESTATUS[1]} -eq 1 ]; then
    echo ${base_url}$c1$c2$c3$c4$c5$c6 
  fi
done
done
done
done
done
done

#for i in 1 2 ; do
 # curl $CURL_OPTIONS ${BASE_URL}$STRING | rg "<title>"  
#done


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
