// ==UserScript==
// @name        kleinanzeigen arrow key navigator
// @namespace   Violentmonkey Scripts
// @icon        data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMzAwIiBoZWlnaHQ9IjMwMCIgdmlld0JveD0iMCAwIDMwMCAzMDAiIGZpbGw9Im5vbmUiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyI+CiAgPHBhdGggZD0iTTIxMC40ODEgMjk0QzE3MS41NCAyOTQgMTUyLjQ4OSAyNjYuOTYyIDE0OC42MiAyNjEuMzc1QzEzNy4xMjYgMjcyLjYzIDExOS43NzQgMjk0IDg4LjUxODcgMjk0QzUyLjQyNTcgMjk0IDIyIDI2Ni44NjIgMjIgMjIyLjg2MlY3Ni4xMzhDMjIgMzIuMDM3OCA1Mi40NzU3IDUgODguNTE4NyA1QzEyNC41NjIgNSAxNTUuMDM3IDMzLjcxNzEgMTU1LjAzNyA3NS40NjgzQzE2Mi4wNzQgNzIuOTc5NCAxNjkuNTQxIDcxLjY5IDE3Ny4yMTcgNzEuNjlDMjE0LjM0OSA3MS42OSAyNDMuNzM2IDEwMS45NzYgMjQzLjczNiAxMzguMzhDMjQzLjczNiAxNDguNTg1IDI0MS43OTcgMTU3LjY2MSAyMzcuNTU5IDE2Ni4zNTdDMjYxLjEwNyAxNzYuODUzIDI3NyAyMDAuNDcyIDI3NyAyMjcuMzFDMjc3IDI2NC4wODMgMjQ3LjE1NCAyOTQgMjEwLjQ4MSAyOTRaTTE2NC43MDMgMjQ1LjI1MkMxNzQuMjY4IDI2MS45MTQgMTkwLjM3MSAyNzEuNzcgMjEwLjQ4MSAyNzEuNzdDMjM0LjkzIDI3MS43NyAyNTQuODMgMjUxLjgxOSAyNTQuODMgMjI3LjMxQzI1NC44MyAyMDcuOTE5IDI0Mi4zOTYgMTkxLjA0NiAyMjQuNTA1IDE4NS4xMDlMMTY0LjcwMyAyNDUuMjYyVjI0NS4yNTJaTTg4LjUyODcgMjcuMjNDNjYuNDU5MSAyNy4yMyA0NC4xNzk2IDQyLjM1MzIgNDQuMTc5NiA3Ni4xMzhWMjIyLjg2MkM0NC4xNzk2IDI1Ni42NDcgNjYuNDQ5MSAyNzEuNzcgODguNTI4NyAyNzEuNzdDMTA2LjA1IDI3MS43NyAxMTUuNzM2IDI2Mi45MDQgMTMxLjM1OSAyNDcuMjQxTDEzOC4yNzUgMjQwLjMwNEMxMzQuNzA3IDIyOS42MzkgMTMyLjg2OCAyMTcuODA0IDEzMi44NjggMjA1LjA3Vjc2LjEzOEMxMzIuODY4IDQyLjM1MzIgMTEwLjU5OCAyNy4yMyA4OC41MTg3IDI3LjIzSDg4LjUyODdaTTE1NS4wNDcgOTkuODU3M1YyMDUuMDhDMTU1LjA0NyAyMTEuMDM3IDE1NS41NDcgMjE2LjcwNSAxNTYuNDk3IDIyMi4wNDJMMjA0LjY5NCAxNzMuNzI0QzIxOC42OTcgMTU5LjY5IDIyMS41NjYgMTQ5LjQ0NSAyMjEuNTY2IDEzOC4zOUMyMjEuNTY2IDExNC43NzEgMjAyLjYzNSA5My45MyAxNzcuMjE3IDkzLjkzQzE2OS4zMTEgOTMuOTMgMTYxLjc0NCA5NS45NjkxIDE1NS4wNDcgOTkuODY3M1Y5OS44NTczWiIgZmlsbD0iIzYwOUYyOCIvPgo8L3N2Zz4K
// @version     1.0.0
//
// @match       https://www.kleinanzeigen.de/s-*
// @grant       none
//
// @author      -
// @description
// ==/UserScript==
addEventListener("keyup", arrowNavigate);
function arrowNavigate(event){
  if(event.getModifierState("Alt")
    ||window.location.pathname.startsWith('/s-anzeige')){
    return;
  }
  switch(event.key){
    case "ArrowLeft":
      handleNavigate(event, -1);
      break;
    case "ArrowRight":
      handleNavigate(event, +1);
      break;
  }
}

function handleNavigate(event,offset){
  event.preventDefault();
  url = new URL(window.location.href);
  regex = /seite:(\d*)/;
  split = url.pathname.split("/");
  // if on page 1, only allow forward navigator by manually adding pagination string
  if (!regex.test(url.pathname)  && event.key == "ArrowRight") {
    split.splice(split.length - 1, 0, "seite:2");
    url.pathname = split.join("/");
  }
  else{
    url.pathname = url.pathname.replace(regex, (matched, capture) =>{return matched.replace(capture, Number(capture)+offset)});
  }
  //console.log(url.toString());
  window.location.href = url.toString();
}
