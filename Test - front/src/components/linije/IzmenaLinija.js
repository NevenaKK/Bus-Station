import React, { useCallback, useEffect, useState } from "react";
import CinemaAxios from "../../apis/CinemaAxios";
import {Form, Button, Col, Row } from "react-bootstrap";
import { useNavigate, useParams } from "react-router-dom";


const IzmenaLinija =()=>{

    const routeParams=useParams()
    const navigate=useNavigate()

    const [linija,setLinija]=useState({})
    const [prevoznici,setPrevoznici]=useState([])

    const getLinijaById=()=>{
        CinemaAxios.get("/linije/"+routeParams.id)
            .then((res)=>{
                console.log(res)
                setLinija(res.data)
            })
            .catch((error)=>{
                alert("Nije uspelo dobavljanje")
            })
    }

    const getPrevoznici=useCallback(()=>{
        CinemaAxios.get("/prevoznici")
            .then(res=>{
                console.log(res)
                setPrevoznici(res.data)
                
            })
            .catch(error=>{
                alert("Neuspesno dobavljanje podataka !")
            })
    },[])

    useEffect(()=>{
        getPrevoznici()
        getLinijaById()
    },[])


    const inputValueChanged=(e)=>{

        let editLinija={...linija}
        const {name,value}=e.target;
        editLinija[name]=value;
        setLinija(editLinija);
    }

    const edit=()=>{
        CinemaAxios.put("/linije/"+routeParams.id,linija)
            .then((res)=>{
            console.log(res)
            navigate("/linije");
          

        })
        .catch((error)=>{
            console.log(error)
            alert("Nije uspelo cuvanje")
        })
    }

    return(
        <div>
        <Row>
            <Col> <h1>Izmena linije </h1></Col>
        <Col>
        <Form>
        <Form.Group>
                    <br/>

                <Form.Group>
                    <Form.Label>Prevoznik</Form.Label>
                    <Form.Control id="stanje" as='select' name="prevoznikId" onChange={inputValueChanged}>
                        <option>{linija.prevoznikNaziv}</option>
                        {
                            prevoznici.map((prevoznik)=>{
                                return <option key={prevoznik.id} value={prevoznik.id} >{prevoznik.naziv}</option>
                            })
                        }
                    </Form.Control>
                </Form.Group>

                <Form.Group>
                    <Form.Label>Destinacija</Form.Label>
                    <Form.Control id="destinacija" name="destinacija" 
                                    value={linija.destinacija} onChange={inputValueChanged} ></Form.Control>
                </Form.Group>

                <Form.Label>Broj mesta</Form.Label>
                    <Form.Control id="brojMesta" type="number" name="brojMesta" 
                                    value={linija.brojMesta} onChange={inputValueChanged}></Form.Control>
                </Form.Group>

                <Form.Group>
                    <Form.Label>Vreme polaska</Form.Label>
                    <Form.Control id="vremePolaska" type="time" name="vremePolaska" 
                                    value={linija.vremePolaska} onChange={inputValueChanged}></Form.Control>
                </Form.Group>

                <Form.Group>
                    <Form.Label>Cena karte</Form.Label>
                    <Form.Control id="cenaKarte" type="number" name="cenaKarte" 
                                    value={linija.cenaKarte} onChange={inputValueChanged}></Form.Control>
                </Form.Group>

            <br/>
            <Button onClick={()=>edit()} >Edit</Button>
        </Form>
        </Col>
        <Col></Col>
    </Row>

    </div>
    )
}

export default IzmenaLinija;
