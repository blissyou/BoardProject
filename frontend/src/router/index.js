import {createWebHistory,createRouter} from "vue-router";
import HelloWorld from "../components/HelloWorld.vue"


const routes = [{
        path:"/",
        component:HelloWorld,
        name:"home"
    }]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes,
})

export default router;