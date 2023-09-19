#!/bin/bash
eza --only-dirs | while read -r line; do
    mv $line* "$line/"
done

