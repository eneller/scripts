// ==UserScript==
// @name        annas-archive auto-downloader
// @namespace   Violentmonkey Scripts
// @match       http*://annas-archive.tld/slow_download/*
// @grant       none
// @version     1.0
// @author      eneller
// ==/UserScript==
downloadButton = document.querySelector('a.font-bold');
if (downloadButton!= null){
  downloadButton.click();
  console.log('found: '+downloadButton.href);
}

