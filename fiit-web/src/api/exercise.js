import { Api } from "@/api/api.js";

export { ExerciseApi, ExerciseInfo }

class ExerciseApi {

    static getUrl(slug) {
        return `${Api.baseUrl}/exercises${slug ? `/${slug}` : ''}`;
    }

    static async getAllExercises() {
        return await Api.get(ExerciseApi.getUrl(), true);
    }

    static async addExercise(name, detail, type) {
        return await Api.post(ExerciseApi.getUrl(), true, {name: name, detail:detail, type:type});
    }

    static async getExercise(id) {
        return await Api.get(ExerciseApi.getUrl(id), true);
    }

    static async modifyExercise(id, basicExercise) {
        return await Api.put(ExerciseApi.getUrl(id), true, {...basicExercise});
    }

    static async removeExercise(id) {
        return await Api.delete(ExerciseApi.getUrl(id), true);
    }

    static async addExerciseImage(id, url) {
        return await Api.post(`${ExerciseApi.getUrl(id)}/images`, true, {number: 1, url: url});
    }

    static async getExerciseImage(id) {
        const res = await Api.get(`${ExerciseApi.getUrl(id)}/images`, true);
        return res.content[0];
    }

    static async modifyExerciseImage(id, url) {
        return await Api.put(`${ExerciseApi.getUrl(id)}/images/1`, true, {number: 1, url: url});
    }

    static async removeExerciseImage(id) {
        return await Api.delete(`${ExerciseApi.getUrl(id)}/images/1`, true);
    }

    static async addExerciseVideo(id, url) {
        return await Api.post(`${ExerciseApi.getUrl(id)}/videos`, true, {number: 1, url: url});
    }

    static async getExerciseVideo(id) {
        try{
            const res = await Api.get(`${ExerciseApi.getUrl(id)}/videos/1`, true);
            return res
        } catch{
            return {url: ''}
        }
    }

    static async modifyExerciseVideo(id, url) {
        return await Api.put(`${ExerciseApi.getUrl(id)}/videos/1`, true, {number: 1, url: url});
    }

    static async removeExerciseVideo(id) {
        return await Api.delete(`${ExerciseApi.getUrl(id)}/videos/1`, true);
    }

    static async getMyExecrcicesByPage(page) {
        return await Api.get(ExerciseApi.getUrl()+`?page=${page}`, true);
    }
}

class ExerciseInfo {
    constructor(name, detail, metadata) {
        this.name = name;
        this.detail = detail;
        this.metadata = metadata;
    }
}