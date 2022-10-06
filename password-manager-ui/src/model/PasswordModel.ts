import { AbstractModel } from "./AbstractModel";
import { User } from "./UserModel";

export class Password extends AbstractModel {
    name!: string
    description!: string
    url!: string
    password!: string
    user!: User

    constructor() {
        super()
    }
}
