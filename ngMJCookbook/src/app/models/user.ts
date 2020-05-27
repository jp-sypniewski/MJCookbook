import { Profile } from './profile';

export class User {
  username: string;
  password: string;
  profile: Profile;
  enabled: boolean;
  role: string;

  constructor(username?: string, password?: string, profile?: Profile,
    enabled?: boolean, role?: string){
      this.username = username;
      this.password = password;
      this.profile = profile;
      this.enabled = enabled;
      this.role = role;
    }

}
