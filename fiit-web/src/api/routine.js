import { Api } from "@/api/api.js";

export { RoutineApi }

class RoutineApi {
    
    static getUrl(slug) {
        return `${Api.baseUrl}/routines${slug ? `/${slug}` : ''}`;
    }

    static async getRoutines() {
        return await Api.get(RoutineApi.getUrl(), true);
    }

    static async addRoutine() {
        return await Api.post(RoutineApi.getUrl(), true);
    }
}

