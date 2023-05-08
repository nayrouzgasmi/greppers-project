export const userRoles:any[] = JSON.parse(sessionStorage.getItem('roles') as string) ;
export const isMarchant:boolean=userRoles.some(user=>user.roleName==="Marchant");
export const isAdmin:boolean=userRoles.some(user=>user.roleName==="Admin");
export const id:any=sessionStorage.getItem('id');