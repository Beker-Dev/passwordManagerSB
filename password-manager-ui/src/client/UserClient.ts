import { User } from "@/model/UserModel";
import { PageRequest } from "@/model/page/page-request";
import { PageResponse } from "@/model/page/page-response";
import axios, { AxiosInstance } from "axios";


export class UserClient {
    private axiosClient: AxiosInstance;

    constructor() {
        this.axiosClient = axios.create({
            baseURL: 'http://localhost:8080/api/users',
            headers: { "Content-Type": "application/json" },
        });
    }

    public async findById(id: number): Promise<any> {
        try {
            return (await this.axiosClient.get<any>(`/${id}`)).data
        } catch (error:any) {
            return Promise.reject(error.response)
        }
    }

    public async findAll(pageRequest : PageRequest): Promise<any> {
        try {

            let requestPath = ''

            requestPath += `?page=${pageRequest.currentPage}`
            requestPath += `&size=${pageRequest.pageSize}`
            requestPath += `&sort=${pageRequest.sortField === undefined
                ? '' : pageRequest.sortField},${pageRequest.direction}`

            return (await this.axiosClient.get<any>(requestPath,
                {
                    params: { filtros: pageRequest.filter }
                }
            )).data
        } catch (error:any) {
            return Promise.reject(error.response)
        }
    }

    public async save(user: User): Promise<any> {
        try {
            return (await this.axiosClient.post('/', user))
        } catch (error:any) {
            return Promise.reject(error.response)
        }
    }

    public async login(user: User): Promise<any> {
        try {
            return (await this.axiosClient.post('/login', user))
        } catch (error:any) {
            return Promise.reject(error.response)
        }
    }

    public async update(user: User): Promise<any> {
        try {
            return (await this.axiosClient.put('/login', user)).data
        } catch (error:any) {
            return Promise.reject(error.response)
        }
    }

    public async delete(user: User): Promise<void> {
        try {
            return (await this.axiosClient.delete(`/${user.id}`)).data
        } catch (error:any) {
            return Promise.reject(error.response)
        }
    }
}
