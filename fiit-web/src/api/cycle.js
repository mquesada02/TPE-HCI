import { Api } from "@/api/api.js";

export { CycleApi }

class CycleApi {

    static getUrl(id, slug) {
        return `${Api.baseUrl}/cycles/${id}/exercises${slug ? `/${slug}` : ''}`;
    }

    static async retrieveExercises(cycleId) { 
        return await Api.get(CycleApi.getUrl(cycleId), true)
    }

    static async getExerciseById(id, exerciseId) {
        return await Api.get(CycleApi.getUrl(id, true, exerciseId));
    }

    static async addExercise(id, exerciseId, exerciseBody) {
        return await Api.post(CycleApi.getUrl(id, exerciseId), true, exerciseBody);
    }

}