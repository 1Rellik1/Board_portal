<template>
  <div v-if="visible" :class="$style.modalContainer">
    <div v-click-outside="checkClickOutside" :class="$style['modalContainer__modal']">
      <i :class="$style['modalContainer__modal__close']" @click="$emit('close')">
        <close/>
      </i>
      <slot></slot>
    </div>
  </div>
</template>

<script>
import Close from '@/assets/images/icons/Close.vue';

export default {
  name: 'VModal',
  components: {Close},
  props: {
    visible: {type: Boolean, default: false}
  },
  methods: {
    checkClickOutside() {
      this.$emit('close');
    }
  }
};
</script>

<style lang="scss" module>
.modalContainer {
  position: fixed;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 10;
  overflow-y: hidden;
  background-color: rgba(0, 0, 0, 0.5);

  &__modal {
    position: relative;
    display: flex;
    flex-direction: column;
    background-color: #212330;
    padding: 40px 30px;
    border-radius: 20px;

    &__close {
      position: absolute;
      top: 16px;
      right: 16px;
      cursor: pointer;
    }
  }
}
</style>
