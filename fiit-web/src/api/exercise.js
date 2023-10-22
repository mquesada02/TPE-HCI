import { Api } from "@/api/api.js";

export { ExerciseApi, Exercise, BasicExercise, ExercisePair }

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
        return await Api.get(`${ExerciseApi.getUrl(id)}/images/1`, true);
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
        return await Api.get(`${ExerciseApi.getUrl(id)}/videos/1`, true);
    }

    static async modifyExerciseVideo(id, url) {
        return await Api.put(`${ExerciseApi.getUrl(id)}/videos/1`, true, {number: 1, url: url});
    }

    static async removeExerciseVideo(id) {
        return await Api.delete(`${ExerciseApi.getUrl(id)}/videos/1`, true);
    }

}

class BasicExercise {
    constructor(name, detail, type) {
        this.name = name;
        this.detail = detail;
        this.type = type;
    }

    get BasicData() {
        return {
            name: this.name,
            detail: this.detail,
            type: this.type
        }
    }
}

class Exercise extends BasicExercise {
    constructor(name, detail, type, img , video , equipment, muscle) {
        super(name, detail, type);
        this.img = img;
        this.video = video;
        this.equipment = equipment;
        this.muscle = muscle;
    }

    get MetaData() {
        return {
            metadata: {
                equipment: this.equipment,
                muscle: this.muscle
            }
        }
    }

    get Image() {
        return this.img;
    }

    get Video() {
        return this.video;
    }
}

class ExercisePair {
    constructor(exercise, reps, secs) {
        this.exercise = exercise;
        this.reps = reps;
        this.secs = secs;
    }
}