<template>
  <input
      v-model="internalValue"
      :type="type"
      :placeholder="placeholder"
      @keyup="$emit('keyup', $event.target.value)"
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

</style>