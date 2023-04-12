#!/bin/bash
dotfiles ls-files | while read -r line; do
    mv $HOME/$line $HOME/$line.bak
done

