import { defineStore } from "pinia";
import { ref } from "vue";

export const useUserStore = defineStore('user', () => {
    // states

    const currentUser = ref(null);
    const logInStatus = ref(false);

    // getters

   // LOGIN STATUS

   const getLogInStatus = () => logInStatus.value;

    // USERS
    async function fetchUsers() {
        const response = await fetch('http://localhost:8080/api/users');
        if (!response.ok) {
            throw new Error(response.statusText);
        }
        return response.json();
    } 

    async function getUsers() {
        try {
            let data = await fetchUsers();
        } catch(error) {
            console.log(`Unexpected error: ${error.message}`);
        }
        return data;
    }

    // USER
    async function getUser(user) {
        let data = await getUsers();
        return data.find(u => u.username === user);
    }

    // USER FAVOURITES

    async function fetchUserFavorites() {
        const response = await fetch('http://localhost:8080/api/favourites')
        if (!response.ok) {
            throw new Error(response.statusText);
        }
        return response.json();
    }

    async function getUserFavourites() {
        try {
            let data = await fetchUserFavorites();
        } catch(error) {
            console.log(`Unexpected error: ${error.message}`);
        }
        return data;
    }

    // CURRENT ROUTINES

    async function fetchCurrentRoutines() {
        const response = await fetch('http://localhost:8080/api/users/current/routines');
        if (!response.ok) {
            throw new Error(response.statusText);
        }
        return response.json();
    }  

    async function getCurrentRoutines() {
        try {
            let data = await fetchCurrentRoutines();
        } catch(error) {
            console.log(`Unexpected error: ${error.message}`);
        }
        return data;
    }

    // CURRENT USER

    async function fetchCurrentUser() {
        const response = await fetch('http://localhost:8080/api/users/current');
        if (!response.ok) {
            throw new Error(response.statusText);
        }
        return response.json();
    }

    async function getCurrentUser() {
        if (currentUser.value) {
            return currentUser.value;
        } else {
            try {
                let data = await fetchCurrentUser();
            } catch(error) {
                console.log(`Unexpected error: ${error.message}`);
            }
            return data;
        }
    }

    async function postCurrentUser(currentUser) {
        var user = {
            firstName : currentUser.firstName,
            lastName : currentUser.lastName,
            gender : currentUser.gender,
            birthdate: currentUser.birthdate,
            phone: currentUser.phone,
            avatarUrl : currentUser.avatarUrl,
            metadata : currentUser.metadata
        }
        var init = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json; charset=utf-8',
            },
            body: JSON.stringify(user)
        }

        try {
            await fetch('http://localhost:8080/users/current', init)
        } catch(error) {
            console.log(`Unexpected error: ${error.message}`);
        }

    }

    async function deleteCurrentUser() {
        var init = {
            method: 'DELETE',
        }
        try {
            await fetch('http://localhost:8080/users/current', init)
        } catch(error) {
            console.log(`Unexpected error: ${error.message}`);   
        }

    }
    

    // actions
    async function fetchLogIn(user, pass) {
        var log = {
            username : user,
            password : pass
        }

        var init = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json; charset=utf-8',
            },
            body: JSON.stringify(log)
        }

        try {
            await fetch('http://localhost:8080/users/login', init) // returns token
            return true;
        } catch (error) {
            console.log(`Unexpected error: ${error.message}`);
            return false;
        }
    }


    async function logIn(user, pass) {

        let success = await fetchLogIn(user, pass);
        if (success) {
            logInStatus.value = true;
            // currentUser.value = await getUser(user); // asumiendo que el login hace put del current
            // postCurrentUser(user);
            // redirect
        }
        return success;
    }

    async function fetchLogOut() {
        var init = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json; charset=utf-8',
            }
        }

        try {
            await fetch('http://localhost:8080/users/logout', init) 
            return true;
        } catch (error) {
            console.log(`Unexpected error: ${error.message}`);
            return false;
        }
    }

    function logOut() {
        let success = fetchLogOut();
        if (success) {
            logInStatus.value = false;
            // redirect
        }
        return success;
    }

    async function fetchRegister(email, pass, name, surname, user, birthdate, height, weight) {
        let [day, month, year] = birthdate.split('-');

        var init = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json; charset=utf-8',
            },
            body: {
                username: user,
                password: pass,
                firstName: name,
                lastName: surname,
                gender: null,
                birthdate: new Date(+year,+month,+day).valueOf(),
                email: email,
                phone: null,
                avatarUrl: null,
                metadata: null
            }
        }

        try {
            await fetch('http://localhost:8080/users', init) 
            return true;
        } catch (error) {
            console.log(`Unexpected error: ${error.message}`);
            return false;
        }

    }

    async function register(email, pass, name, surname, user, birthdate, height, weight) {
        let u = await getUser(user);
        if (!u) {
            let success = await fetchRegister(email, pass, name, surname, user, birthdate, height, weight);
            if (success) {
                // send mail verification
            }
        } else {
            console.log("Ese usuario ya existe.");
            return false;
        }
    }

    async function modifyCurrentUser(name,surname,birthdate,height,weight) {
        let user = await getCurrentUser();
        if (name) {
            user.firstName = name;
        }
        if (surname) {
            user.lastName = surname;
        }
        if (birthdate) {
            let [day, month, year] = birthdate.split('-');
            user.birthdate = new Date(+year,+month,+day).valueOf();
        }
        if (height) {
            user.metadata.height = height;
        }
        if (weight) {
            user.metadata.weight = weight;
        }
        postCurrentUser(user);

    }

    // falta addFavourite, removeFavourite, addRoutine, removeRoutine

    
    

    return {
        // states
        currentUser,
        logInStatus,

        // getters
        getLogInStatus,
        getUsers,
        getUser,
        getUserFavourites,
        getCurrentRoutines,
        getCurrentUser,
        postCurrentUser,
        deleteCurrentUser,

        // actions
        logIn,
        logOut,
        register,
        modifyCurrentUser
    }



});