<template>
  <input v-model="internalValue"
         :class="{[$style.filledInput]: internalValue.length > 0}"
         placeholder="Поиск"
         type="text"
  />
</template>

<script>
export default {
  name: 'VSearchInput',
  props: {
    value: {type: String, default: ''},
  },
  data() {
    return {
      internalValue: this.value,
    };
  },
};
</script>

<style lang="scss" module>
input {
  width: 166px;
  height: 40px;
  border: 1px solid rgba(0, 0, 0, 0.1);
  border-radius: 25px;
  background: #12131E url("@/assets/svg/icons/search.svg") no-repeat center left 12px;
  padding: 0 12px 0 57px;
  color: #fff;
  transition: .2s;

  &::placeholder {
    color: #fff;
  }

  &:focus {
    width: 300px;

    &::-webkit-input-placeholder {
      color: transparent
    }

    &::-moz-placeholder {
      color: transparent
    }

    &::-ms-input-placeholder {
      color: transparent
    }
  }
}

.filledInput {
  width: 300px;
}
</style>