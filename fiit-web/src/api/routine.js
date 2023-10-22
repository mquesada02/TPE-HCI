import { Api } from "@/api/api.js";

export { RoutineApi, CycleInfo }

class RoutineApi {
    
    static getUrl(slug) {
        return `${Api.baseUrl}/routines${slug ? `/${slug}` : ''}`;
    }

    static getSearchUrl(query) {
        return RoutineApi.getUrl() + `?search=${query}`;
    }

    static async getRoutines() {
        return await Api.get(RoutineApi.getUrl(), false);
    }

    static async getFilteredRoutines(query) {
        return await Api.get(RoutineApi.getSearchUrl(query), false);
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
        this.series = series;
    }
}


