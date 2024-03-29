import React from 'react';
import { createRoot } from 'react-dom/client';
import {Link, Route,BrowserRouter as Router, Routes} from 'react-router-dom';
import Home from './components/Home';
import NotFound from './components/NotFound';
import Login from './components/authorization/Login';
import { logout } from './services/auth';
import { Button, Nav, Navbar, NavbarBrand ,Container} from 'react-bootstrap';
import Linija from './components/linije/Linija';
import IzmenaLinija from './components/linije/IzmenaLinija';
import Rezervacija from './components/linije/Rezervacija';



const App = () => {

    if(window.localStorage['jwt']){
        return( 
        
                <Router>

                <Navbar expand bg="dark" variant='dark'>   

                    <NavbarBrand as={Link} to="/"> Test </NavbarBrand>  
                    <Nav.Link as={Link} to="/linije"> Linije </Nav.Link>
                    <Nav>
                        <Button onClick={()=>logout()} >Logout</Button>
                    </Nav>
                </Navbar>
    
    		<Container style={{paddingTop:"10px"}}>
                <Routes>
                    <Route path='/' element={<Home/>} />      
                    <Route path='/login' element={<Login/>} />
                    <Route path='/linije' element={<Linija/>} />
                    <Route path='/linije/edit/:id' element={<IzmenaLinija/>} />
                    <Route path='/linije/rezervacije/:id' element={<Rezervacija/>} />
                    <Route path='*'  element={<NotFound/>}/>
                </Routes>
                </Container>
                </Router>
    
    
        )
    }else{
        return( 
          
                <Router>

                <Navbar expand bg="dark" variant='dark'> 
                     <NavbarBrand as={Link} to="/"> Test </NavbarBrand>     
                     <Nav.Link as={Link} to="/linije"> Linije </Nav.Link>   
                     <Nav.Link as={Link} to="/login" >Login</Nav.Link>
                </Navbar>
                
    		<Container style={{paddingTop:"10px"}}>
                <Routes>
                    <Route path='/' element={<Home/>} />      
                    <Route path='/login' element={<Login/>} />
                    <Route path='/linije' element={<Linija/>} />
                    <Route path='*'  element={<NotFound/>}/>
                </Routes>
  		</Container>
                </Router>
    
    
        
    
        )
    }

    

};


const rootElement = document.getElementById('root');
const root = createRoot(rootElement);

root.render(
    <App />,
);
 
