import ApiParams from "./ApiParams";

export default class ApiParamsFactory {
    constructor(private readonly baseUrl: string) {}

    public login(login: string, password: string): ApiParams {
        let params = new ApiParams(this.baseUrl, '/api/login', 'POST');
        params.addBodyField("login", login);
        params.addBodyField("password", password);
        
        return params;
    }


    public register(login: string, password: string): ApiParams {
        let params = new ApiParams(this.baseUrl, '/api/register', 'POST');
        params.addBodyField("login", login);
        params.addBodyField("password", password);
        
        return params;
    }
}