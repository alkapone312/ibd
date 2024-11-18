import ApiParamsFactory from "./ApiParamsFactory";
import ApiService from "./ApiService";

export default class ApiFacade {
    private apiService;
    private apiParamsFactory;
    private loginCallback;
    
    constructor(apiBase: string) {
        this.apiService = new ApiService();
        this.apiParamsFactory = new ApiParamsFactory(apiBase);
    }

    public isLogged() {
        let token = localStorage.getItem('authToken');

        return !!token && token.length != 0;
    }

    public onLoginChange(callback: (isLogged: boolean) => void) {
        this.loginCallback = callback;
    }

    public async login(login: string, password: string) {
        let object = await this.apiService.request(this.apiParamsFactory.login(login, password))
        if(!object.token) {
            throw new Error("Something went wrong!");
        }
        localStorage.setItem('authToken', object.token);
        this.loginCallback(true);
    }

    public logout(): void {
        localStorage.clear();
        this.loginCallback(false);
    }

    public async register(login: string, password: string) {
        let object = await this.apiService.request(this.apiParamsFactory.register(login, password))
        
        if(!object.status || object.status != "ok") {
            throw new Error("Something went wrong!");
        }
    }
}