<template>
  <span></span>
</template>
<script>
export default {
  created() {
    this.$store.dispatch('destroyToken').then(() => {
      this.$router.push({name: 'login'})
    }).catch((err) => {
      this.$router.push({name: 'login'})
      console.log(err)
    })
  }
}
</script>