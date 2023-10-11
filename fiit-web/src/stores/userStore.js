import { defineStore } from "pinia";
import { ref } from "vue";

export const useUserStore = defineStore('user', () => {
    // states
    const users = ref([
        /*{
            user: '',
            password: '',
            email: '',
            name: '',
            surname: '',
            birthday: '',
            height: 0,
            weight: 0,
            favourites: [],
            routines: [],
        }
        */
    ]);

    const currentUser = ref(null);
    const logInStatus = ref(false);

    // getters
    const getUsers = () => users.value;
    
    const getUser = (user) => {getUsers().find( u => u.user === user)};
    const getUserByEmail = (email) => {users.value.find(u => u.email === email)};
    const getUserFavourites = (user) => {users.value.find(u => u.user === user).favourites};
    const getUserRoutines = (user) => {users.value.find(u => u.user === user).routines};

    const getLogInStatus = () => logInStatus.value;

    // actions
    function logIn(user, pass) {
        const u = getUser(user);
        if (u && u.password === pass) {
            logInStatus.value = true; 
            return true;
        } else {
            console.log("Usuario o contraseña incorrecta."); 
            return false;
        } 
    }

    function register(email, pass, name, surname, user, birthday, height, weight) {
        const u = getUser(user);
        if (!u) {
            users.value.push({
                user: user,
                password: pass,
                email: email,
                name: name,
                surname: surname,
                birthday: birthday,
                height: height,
                weight: weight,
                favourites: [],
                routines: [],
            });
            logInStatus.value = true;
            console.log('Registro completo.')
            return true;
        } else {
            console.log("Ese usuario ya existe.");
            return false;
        }
    }

    function logOut() {
        logInStatus.value = false;
    }

    function deleteUser(user) {
        const u = getUser(user);
        if (u) {
            users.value.splice(users.value.indexOf(u), 1);
            logInStatus.value = false;
        } else {
            console.log("Ese usuario no existe.");
        }
    }

    function modifyUser(user, pass, email, name, surname, birthday, height, weight) {
        const u = getUser(user);
        if (u) {
            u.password = pass;
            u.email = email;
            u.name = name;
            u.surname = surname;
            u.birthday = birthday;
            u.height = height;
            u.weight = weight;
        } else 
            console.log("Ese usuario no existe.");
        
    }

    function addFavourite(user, routine) {
        const u = getUser(user);
        if (u) 
            u.favourites.push(routine);
        else 
            console.log("Ese usuario no existe.");
        
    }

    function removeFavourite(user, routine) {
        const u = getUser(user);
        if (u) 
            u.favourites.splice(u.favourites.indexOf(routine), 1);
        else 
            console.log("Ese usuario no existe.");
        
    }

    function addRoutine(user, routine) {
        const u = getUser(user);
        if (u) 
            u.routines.push(routine);
        else 
            console.log("Ese usuario no existe.");
        
    }

    function removeRoutine(user, routine) {
        const u = getUser(user);
        if (u) 
            u.routines.splice(u.routines.indexOf(routine), 1);
        else 
            console.log("Ese usuario no existe.");
        
    }

    return {
        // states
        users,
        logInStatus,

        // getters
        getUsers,
        getUser,
        getUserByEmail,
        getUserFavourites,
        getUserRoutines,
        getLogInStatus,

        // actions
        logIn,
        register,
        logOut,
        deleteUser,
        modifyUser,
        addFavourite,
        removeFavourite,
        addRoutine,
        removeRoutine,
    }



});