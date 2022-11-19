import { useKeycloak } from '@react-keycloak/web'
import { Link } from 'react-router-dom'
import axios from 'axios'
import jwt_decode from "jwt-decode";

export default function Index() {
  const { keycloak } = useKeycloak()
  // Here you can access all of keycloak methods and variables.
  // See https://www.keycloak.org/docs/latest/securing_apps/index.html#javascript-adapter-reference
  const decodeToken = (token) => {
    const decoded = jwt_decode(token);
    console.log(decoded)
    return decoded;
  }
  const getTodo = async (token) => {
    return axios.get(`/justificatives`, {
      headers: { 'Authorization': `Bearer ${token}` }
    }).then(response => {
      console.log(response.data);
    })
  }
  const getMovie = async (id, token) => {
    return axios.get(`/justificatives/${id}`, {
      headers: { 'Authorization': `Bearer ${token}` }
    }).then(response => {
      console.log(response.data);
    })
  }
  const postMovie = async (token) => {
    return axios.post(`/justificatives`,{
      "rut_employee": "20457671-9",
      "date": "2022-02-02"
    },
     {
      headers: { 'Authorization': `Bearer ${token}` }
    }).then(response => {
      console.log(response.data);
    })
  }
  return (
    <div>
      <Link to='/upload'><h1>Subir Archivo</h1></Link>
      {
        keycloak.authenticated ?
        <div>
          <h4>
            El usuario autenticado es {decodeToken(keycloak.token).preferred_username}
          </h4>
          <button  onClick={() => keycloak.logout()}>Logout</button>
        </div>
        :
        <button  onClick={() => keycloak.login()}>Login</button>
      }
      <button onClick={()=>getMovie(1, keycloak.token)}>GET 1 WEA</button>
      <button onClick={()=>getTodo(keycloak.token)}>GET WEAS</button>
      <button onClick={()=>postMovie(keycloak.token)}>POST WEAS</button>
    </div>)
}