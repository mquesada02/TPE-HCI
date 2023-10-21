import { Api } from "@/api/api.js";

export { RoutineApi }

class RoutineApi {
    
    static getUrl(slug) {
        return `${Api.baseUrl}/routines${slug ? `/${slug}` : ''}`;
    }

    static async getRoutines() {
        return await Api.get(RoutineApi.getUrl(), false);
    }

    static async createRoutine(name, detail, state, difficulty, metadata) {
        return Api.post(RoutineApi.getUrl(), true, {name: name, detail: detail, isPublic: state, difficulty: difficulty, metadata: metadata});
    }

}


