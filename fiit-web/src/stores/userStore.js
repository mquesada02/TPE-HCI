import { ref, computed } from 'vue';
import { defineStore } from 'pinia';
import { UserApi } from '@/api/user';
import { Api } from '@/api/api';

const SECURITY_TOKEN_KEY = 'security-token'

export const useUserStore = defineStore('user', () => {
    const token = ref(null);
    const user = ref(null);

    const isLoggedIn = computed(() => {
        return token.value
    });

    function initialize() {
        const localToken = localStorage.getItem(SECURITY_TOKEN_KEY);
        if (localToken) {
            setToken(localToken)
        }
    }

    function setToken(newToken) {
        token.value = newToken;
        Api.token = newToken;
    }

    function updateToken(token, rememberMe) {
        if (rememberMe) {
            localStorage.setItem(SECURITY_TOKEN_KEY, token);
        }
        setToken(token);
    }

    async function register(credentials, userInfo) {
        await UserApi.register(credentials, userInfo);
    }

    async function login(credentials, rememberMe) {
        const result = await UserApi.login(credentials);
        updateToken(result.token, rememberMe);
    }

    async function verifyEmail(email, code) {
        await UserApi.verifyEmail(email, code);
    }

    async function logout() {
        const result = await UserApi.logout();
        localStorage.removeItem(SECURITY_TOKEN_KEY);
        setToken(null);
    }

    async function getCurrentUser() {
        if (user.value) 
            return user.value;

        const result = await UserApi.get();
    }

    return {
        isLoggedIn,
        initialize,
        register,
        verifyEmail,
        login,
        logout,
        getCurrentUser

    }
});