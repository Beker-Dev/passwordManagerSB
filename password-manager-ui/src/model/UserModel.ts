import { AbstractModel } from "./AbstractModel";

export class User extends AbstractModel {
    username!: string
    password!: string
    newPw!: string

    constructor() {
        super()
    }
}
