<template>
  <router-view />
</template>

<script>
export default {
  name: 'App',
};
</script>

<style lang="scss">
@import "normalize-css";

body, html {
  font-family: 'Nunito';
  font-style: normal;
  font-weight: 700;
  font-size: 20px;
  line-height: 106.4%;
  background-color: #2A2B3A;
  color: #fff;
  overflow: hidden;
}

a {
  text-decoration: none;
  outline: none !important;
  color: #fff;
}

* {
  ::-webkit-scrollbar {
    width: 16px;
  }

  ::-webkit-scrollbar-track {
    border-radius: 8px;
  }

  ::-webkit-scrollbar-thumb {
    height: 56px;
    border-radius: 8px;
    border: 4px solid transparent;
    background-clip: content-box;
    background-color: #aaaaaa;
  }

  ::-webkit-scrollbar-thumb:hover {
    background-color: #717171;
  }
}
</style>
