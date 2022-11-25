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
          <Route path="/" element={<Index/>}/>
          <Route path='/upload' element={<UploadFile/>} exact/>
          <Route path='/justificatives' element={<PrivateRoute><JustificativeForm/></PrivateRoute>} exact/>
          <Route path='/extra-hours' element={<ExtraHoursForm/>} exact/>
          <Route path='/planilla' element={<Planilla/>} exact/>
        </Routes>
      </Container>
    </Layout>
  </ReactKeycloakProvider>
  );
}

export default App;
