import { Api } from "@/api/api.js";

export { ReviewApi }

class ReviewApi {

    static getUrl(slug) {
        return `${Api.baseUrl}/reviews${slug ? `/${slug}` : ''}`;
    }

    static async getReviews(routineId) {
        return await Api.get(ReviewApi.getUrl(routineId), true);
    }

    static async addReview(routineId, score) {
        return await Api.post(ReviewApi.getUrl(routineId), true, { score: score, review: 'Good review'});
    }

}