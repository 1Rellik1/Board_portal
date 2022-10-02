import axios from "axios";

const AUTH_TOKEN = '';

const $host = axios.create({
    baseURL: 'http://localhost:8081/'
})

$host.defaults.headers.common['Authorization'] = AUTH_TOKEN;

const $authHost = axios.create({
    baseURL: 'https://localhost:8081/'
})

const authInterceptor = config => {
    config.headers.authorization = `Bearer ${localStorage.getItem('token')}`
    return config
}

$authHost.interceptors.request.use(authInterceptor)

export {
    $host,
    $authHost
}