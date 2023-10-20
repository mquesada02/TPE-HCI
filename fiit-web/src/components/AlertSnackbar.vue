<template>
    <div class="text-center">
        <v-snackbar color="error" v-model="snackbar" :timeout="timeout">
            {{ text }}

            <template v-slot:actions>
                <v-btn color="white" variant="text" @click="snackbar = false">
                    Close
                </v-btn>
            </template>
        </v-snackbar>
    </div>
</template>

<script setup>


    import { ref, inject } from 'vue'
    const timeout = ref(6000)
    const snackbar = inject('snackbar')
    const text = inject('text')


</script>