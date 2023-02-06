<template>
  <button
      :type="type"
      :class="{
        [$style.button]: true,
        [$style[`button__${variant}`]]: true
      }"
  >
    <slot></slot>
  </button>
</template>

<script>
export default {
  name: 'VButton',
  props: {
    variant: {type: String, default: 'filled'},
    type: {type: String, default: ''},
  }
};
</script>

<style lang="scss" module>
.button {
  padding: 10px 25px;
  border-radius: 20px;
  border: none;

  font-family: 'Nunito', sans-serif;
  font-style: normal;
  font-weight: 600;
  font-size: 16px;
  line-height: 106.4%;
  color: #fff;

  &__filled {
    background-color: #8600EF;
  }

  &__outline {
    background: none;
    border: 2px solid #8600EF;
  }

  &__outline__white {
    background: none;
    border: 2px solid #fff;
  }

  &:hover {
    transition: .17s;
    opacity: 0.5;
  }
}
</style>