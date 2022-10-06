import { AbstractModel } from "./AbstractModel";

export class User extends AbstractModel {
    username!: string
    password!: string
    url!: string

    constructor() {
        super()
    }
}
