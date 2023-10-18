import { Api } from "@/api/api.js";

export { UserApi, Credentials, UserInfo }

class UserApi {
    static getUrl(slug) {
        return `${Api.baseUrl}/users${slug ? `/${slug}` : ''}`;
    }

    static async register(credentials, userInfo) {
        console.log({credentials,userInfo});
        console.log({...credentials,...userInfo});
        return await Api.post(UserApi.getUrl(), false, { ...credentials, ...userInfo });
    }

    static async resendEmailVerification(email) {
        return await Api.post(UserApi.getUrl('resend_verification'), false, { email });
    }

    static async verifyEmail(email, code) {
        return await Api.post(UserApi.getUrl('verify_email'), false, { email, code });
    }

    static async login(credentials) {
        return await Api.post(UserApi.getUrl('login'), false, credentials);
    }

    static async logout() {
        return await Api.post(UserApi.getUrl('logout'), true);
    }

    static async get() {
        return await Api.get(UserApi.getUrl('current'), true);
    }
}

class Credentials {
    constructor(username, password) {
        this.username = username;
        this.password = password;
    }
}

class UserInfo {
    constructor(firstName, lastName, email, birth, height, weight) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = 'other';
        this.birth = birth; // revisar el formato
        this.email = email;
        this.phone = ''
        this.avatarUrl = ''
        this.metadata = {
            height, weight
        }
    }
}