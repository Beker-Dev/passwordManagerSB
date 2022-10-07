import { AbstractModel } from "./AbstractModel";

export class User extends AbstractModel {
    username!: string
    password!: string

    constructor() {
        super()
    }
}
