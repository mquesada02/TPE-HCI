import { Api } from "@/api/api.js";

export { FavouritesApi }

class FavouritesApi {
    static getUrl(slug) {
        return `${Api.baseUrl}/favourites${slug ? `/${slug}` : ''}`;
    }

    static async getFavourites() {
        return await Api.get(FavouritesApi.getUrl(), true);
    }

    static async addFavourite(routineId) {
        return await Api.post(FavouritesApi.getUrl(routineId), true);
    }

    static async deleteFavourite(routineId) {
        return await Api.delete(FavouritesApi.getUrl(routineId), true);
    }
}