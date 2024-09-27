<script>
    export default{
        name: 'DeveloperItem',
        props: {
            developer:{
                firstName: '',
                lastName: '',
                email: '',
                picture: ''
            }
        }
    }
</script>

<template>
    <div class="w-60 d-flex text-center row mt-5 ml-5">
        <div class="col-2">
            <img :src="`../../../images/${developer.picture}`" alt="Developer avatar" class="rounded-circle w-50" />
        </div>
        <div class="col-4">
            <p>{{ developer.firstName }} {{ developer.lastName }}</p>
            <p>{{ developer.email }}</p>
        </div>     
    </div>
</template>

<style scoped>
</style>