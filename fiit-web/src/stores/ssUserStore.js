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
            return data;
        } catch(error) {
            console.log(`Unexpected error: ${error.message}`);
        }
        return [];
        
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
            return data;
        } catch(error) {
            console.log(`Unexpected error: ${error.message}`);
        }
        return null;
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
            return data;
        } catch(error) {
            console.log(`Unexpected error: ${error.message}`);
        }
        return null;
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
                return data;
            } catch(error) {
                console.log(`Unexpected error: ${error.message}`);
            }
            return null;
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
            await fetch('http://localhost:8080/api/users/current', init)
        } catch(error) {
            console.log(`Unexpected error: ${error.message}`);
        }

    }

    async function deleteCurrentUser() {
        var init = {
            method: 'DELETE',
        }
        try {
            await fetch('http://localhost:8080/api/users/current', init)
        } catch(error) {
            console.log(`Unexpected error: ${error.message}`);   
        }

    }
    

    // actions

    async function verifyEmail(email, code) {
        var info = {
            email: email,
            code: code
        }
        var init = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json; charset=utf-8',
            },
            body: JSON.stringify(info)
        }
        try {
            let response = await fetch('http://localhost:8080/api/users/verify_email', init)
            if (!response.ok) {
                throw new Error(response.statusText);
            } else {
                console.log("Email verificado correctamente.");
                return true;
            }
        } catch(error) {
            console.log("Código incorrecto.");
            return false;
        }
    }

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
            let response = await fetch('http://localhost:8080/api/users/login', init) // returns token
            if (!response.ok) {
                throw new Error(response.statusText);
            } else {
                console.log("Usuario logueado correctamente.");
            }
            return response.json();
        } catch (error) {
            console.log("Usuario y/o contraseña incorrectos.");
            return false;
        }
        
    }


    async function logIn(user, pass) {

        let data = await fetchLogIn(user, pass);
        if (data) {
            logInStatus.value = true;
            localStorage.setItem("token",data);
        }
        return data;
    }

    async function fetchLogOut() {
        var init = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json; charset=utf-8',
            }
        }

        try {
            await fetch('http://localhost:8080/api/users/logout', init) 
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

        var info = {
            username: user,
            password: pass,
            firstName: name,
            lastName: surname,
            gender: 'other',
            birthdate: new Date(+year,+month,+day).valueOf(),
            email: email,
            phone: '',
            avatarUrl: '',
            metadata: null
        };

        var init = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json; charset=utf-8',
            },
            body: JSON.stringify(info)
        }

        try {
            let response = await fetch('http://localhost:8080/api/users', init)
            if (!response.ok) {
                throw new Error(response.statusText);
            } else {
                console.log("Usuario creado correctamente.");
                localStorage.setItem('email',email);
                return true;
            }
        } catch (error) {
            console.log(`Unexpected error: ${error.message}`);
        }
        return false;

    }

    async function register(email, pass, name, surname, user, birthdate, height, weight) {
        return await fetchRegister(email, pass, name, surname, user, birthdate, height, weight);
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
        verifyEmail,
        logIn,
        logOut,
        register,
        modifyCurrentUser
    }



});