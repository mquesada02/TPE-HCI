import { Api } from "@/api/api.js";

export { RoutineApi, CycleInfo }

class RoutineApi {
    
    static getUrl(slug) {
        return `${Api.baseUrl}/routines${slug ? `/${slug}` : ''}`;
    }

    static async getRoutines() {
        return await Api.get(RoutineApi.getUrl(), false);
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

}

class CycleInfo {
    constructor(name, detail, type, series) {
        this.name = name;
        this.detail = detail;
        this.type = type;
        this.series = series;
    }
}


