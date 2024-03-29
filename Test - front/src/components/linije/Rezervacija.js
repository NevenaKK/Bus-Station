import React, {  useState } from "react";
import CinemaAxios from "../../apis/CinemaAxios";
import { useNavigate, useParams } from "react-router-dom";
import {Form, Button, Col, Row, Table } from "react-bootstrap";
import Linija from "./Linija";
const Rezervacija=()=>{

    const routeParams=useParams();
    const [brojMesta,setBrojMesta]=useState(0)
    const navigate=useNavigate();

    

    const rezervisi=()=>{
        CinemaAxios.post("/rezervacije",{
            linijaId:routeParams.id,
            brojMesta:brojMesta


        })

            .then((res)=>{
                console.log(res)
                navigate("/linije")
                alert("Uspesno ste izvrsili rezervaciju !")
            })
            .catch((error)=>{
                console.log(error)
                alert("Nema slobodnih mesta.")
                navigate("/linije")
            })
    }

    return(
        <Row>
           <h1>Rezervacija</h1>

           <Form.Group>
                        <br/>
                        <Form.Label>Broj mesta</Form.Label>
                        <Form.Control id="brojMesta" type="number" name="brojMesta" onChange={(e)=>setBrojMesta(e.target.value)}></Form.Control>
          </Form.Group>   
            <Col>
            <br></br>
            <Button onClick={()=>rezervisi()}>Rezervisi</Button> 
            </Col>
                    
        </Row>
    )

}

export default Rezervacija;