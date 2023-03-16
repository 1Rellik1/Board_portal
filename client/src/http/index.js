import axios from 'axios';

const $host = axios.create({
    baseURL: import.meta.env.VITE_AUTH_API
});

const $authHost = axios.create({
    baseURL: import.meta.env.VITE_AUTH_API
});

const $hostGames = axios.create({
    baseURL: import.meta.env.VITE_GAMES_API
});

const $authHostMonopoly = axios.create({
    baseURL: import.meta.env.VITE_MONOPOLY_API
});
const $authHostBunker = axios.create({
    baseURL: import.meta.env.VITE_BUNKER_API
});


const authInterceptor = config => {
    config.headers.authorization = `Bearer ${localStorage.getItem('token')}`;
    return config;
};

$authHost.interceptors.request.use(authInterceptor);
$authHostMonopoly.interceptors.request.use(authInterceptor);
$authHostBunker.interceptors.request.use(authInterceptor);

export {
    $host,
    $hostGames,
    $authHost,
    $authHostMonopoly,
    $authHostBunker,
};