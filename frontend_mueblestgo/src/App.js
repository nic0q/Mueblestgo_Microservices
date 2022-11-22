import './App.css';
import { Routes, Route } from "react-router-dom";
import Layout from './Layout';
import { React } from 'react';
import { Container } from 'react-bootstrap';
import Index from './pages/Index';
import UploadFile from './pages/UploadFile';
import JustificativeForm from './pages/JustificativeForm';
import ExtraHoursForm from "./pages/ExtraHoursForm"
import PrivateRoute from './components/PrivateRoute';
import Planilla from "./pages/Planilla"
import { ReactKeycloakProvider } from '@react-keycloak/web'

import keycloak from './keycloak'
function App() {
  return (<ReactKeycloakProvider authClient={keycloak}>
    <Layout>
      <Container>
        <Routes>
          <Route path="/" element={<PrivateRoute><Index/></PrivateRoute>}/>
          <Route path='/upload' element={<PrivateRoute><UploadFile/></PrivateRoute>} exact/>
          <Route path='/justificatives' element={<PrivateRoute><JustificativeForm/></PrivateRoute>} exact/>
          <Route path='/extra-hours' element={<ExtraHoursForm/>} exact/>
          <Route path='/planilla' element={<PrivateRoute><Planilla/></PrivateRoute>} exact/>
        </Routes>
      </Container>
    </Layout>
  </ReactKeycloakProvider>
  );
}

export default App;
