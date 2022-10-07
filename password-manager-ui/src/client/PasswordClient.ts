import { Password } from "@/model/PasswordModel";
import { PageRequest } from "@/model/page/page-request";
import { PageResponse } from "@/model/page/page-response";
import axios, { AxiosInstance } from "axios";


export class PasswordClient {
    private axiosClient: AxiosInstance;

    constructor() {
        this.axiosClient = axios.create({
            baseURL: 'http://localhost:8080/api/passwords',
            headers: {'Content-type' : 'application/json'}
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

    public async save(password: Password): Promise<any> {
        try {
            return (await this.axiosClient.post('/', password))
        } catch (error:any) {
            return Promise.reject(error.response)
        }
    }

    public async update(password: Password): Promise<any> {
        try {
            return (await this.axiosClient.put('/', password)).data
        } catch (error:any) {
            return Promise.reject(error.response)
        }
    }

    public async delete(password: Password): Promise<void> {
        try {
            return (await this.axiosClient.delete('/', password)).data
        } catch (error:any) {
            return Promise.reject(error.response)
        }
    }
}
