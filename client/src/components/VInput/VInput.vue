<template>
  <input
      v-model="internalValue"
      :class="{
        [$style['input-container']]: true,
      }"
      :type="type"
      :placeholder="placeholder"
  />
</template>

<script>
export default {
  name: 'VInput',
  props: {
    value: {type: String, default: ''},
    type: {type: String, default: 'text'},
    placeholder: {type: String, default: ''},
  },
  data() {
    return {
      internalValue: this.value,
    };
  }
};
</script>

<style lang="scss" module>
.input-container {
  background: #404350;
  border: none;
  border-radius: 8px;
  padding: 10px 15px;
  color: rgba(255, 255, 255, 0.7);

  &::placeholder {
    color: rgba(255, 255, 255, 0.7);
  }
}
</style>
