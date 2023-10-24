import { Api } from "@/api/api.js";

export { RoutineApi, CycleInfo, Cycle, RoutineInfo }

class RoutineApi {
    
    static getUrl(slug) {
        return `${Api.baseUrl}/routines${slug ? `/${slug}` : ''}`;
    }

    static getSearchUrl(query) {
        return RoutineApi.getUrl() + `?search=${query}`;
    }

    static removeRoutine(id) {
        return Api.delete(RoutineApi.getUrl(id), true);
    }

    static getSearchUrlByPage(page, query) {
        return RoutineApi.getUrl() + `?search=${query}&page=${page}`;
    }

    static async geteCycleExerciseUrl(slugCycle, slugExercise){
        return `${Api.baseUrl}/cycles/${slugCycle}/exercises${slugExercise ? `/${slugExercise}` : ''}`;
    }

    static async getRoutines() {
        return await Api.get(RoutineApi.getUrl(), false);
    }

    static async getRoutinesByPage(page) {
        return await Api.get(RoutineApi.getUrl() + '?page=' + page, false);
    }

    static async getFilteredRoutinesByPage(page, query) {
        return await Api.get(RoutineApi.getSearchUrlByPage(page,query), false);
    }

    static async createRoutine(name, detail, isPublic, difficulty, metadata) {
        return Api.post(RoutineApi.getUrl(), true, {name: name, detail: detail, isPublic: isPublic, difficulty: difficulty, metadata: metadata});
    }

    static async modifyRoutine(id, routineInfo) {
        return Api.put(RoutineApi.getUrl(id), true, routineInfo);
    }

    static async getRoutineById(id){
        return Api.get(RoutineApi.getUrl(id),true);
    }

    static async createCycle(routineId, cycleInfo) {
        return Api.post(RoutineApi.getUrl(routineId + "/cycles"), true, cycleInfo);
    }

    static async addExerciseToCycle(cycleId, exerciseId, body){
        return Api.post(RoutineApi.geteCycleExerciseUrl(cycleId, exerciseId), true, body)
    }

    static async getCycleExercises(cycleId){
        const res= Api.get(RoutineApi.geteCycleExerciseUrl(cycleId))
        const content = res.content
        return content
    }

    static async retrieveRoutineCycles(id){
        const res = await Api.get(RoutineApi.getUrl(id) + '/cycles')
        const content = res.content
        return content
    }

}
class Cycle {
    constructor(cycleInfo, exercisesArray){
        this.cycleInfo = cycleInfo;
        this.exercisesArray = exercisesArray;
    }

}

class CycleInfo {
    constructor(name, detail, type, series) {
        this.name = name;
        this.detail = detail;
        this.type = type;
        this.order = 1;
        this.repetitions = series;
    }

    setOrder(order){
        this.order = order;
    }

    setSeries(series) {
        this.repetitions = series;
    }
}

class RoutineInfo {
    constructor(name, detail, isPublic, difficulty, metadata) {
        this.name = name;
        this.detail = detail;
        this.isPublic = isPublic;
        this.difficulty = difficulty;
        this.metadata = metadata;
    }
}


