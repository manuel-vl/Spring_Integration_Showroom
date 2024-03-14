package com.showroom.exercise.service;

import com.showroom.exercise.dto.ResponseDTO;
import com.showroom.exercise.dto.VentaDTO;
import com.showroom.exercise.exceptions.NotFoundException;
import com.showroom.exercise.model.Prenda;
import com.showroom.exercise.model.Venta;
import com.showroom.exercise.repository.IVentaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VentaService implements IVentaService {
    private final IVentaRepository ventaRepository;
    private final ModelMapper mapper;

    public VentaService(IVentaRepository ventaRepository, ModelMapper mapper) {
        this.ventaRepository = ventaRepository;
        this.mapper = mapper;
    }

    @Override
    public VentaDTO saveVenta(VentaDTO ventaDTO) {
        ventaRepository.save(mapper.map(ventaDTO, Venta.class));
        return ventaDTO;
    }

    @Override
    public List<VentaDTO> getAllVentas() {
        return ventaRepository.findAll().stream().map(venta -> mapper.map(venta, VentaDTO.class)).toList();
    }

    @Override
    public VentaDTO getVentaByNumber(Long number) {
        Venta venta=ventaRepository.findById(number).orElseThrow(()-> new NotFoundException("Venta no encontrada"));

        return mapper.map(venta, VentaDTO.class);
    }

    @Override
    public VentaDTO updateVenta(Long number, VentaDTO ventaDTO) {
        Venta venta=ventaRepository.findById(number).orElseThrow(()-> new NotFoundException("Venta no encontrada"));

        if(ventaDTO.getFecha()!=null) venta.setFecha(ventaDTO.getFecha());
        if(ventaDTO.getTotal()!=null) venta.setTotal(ventaDTO.getTotal());
        if(ventaDTO.getMedioPago()!=null) venta.setMedioPago(ventaDTO.getMedioPago());
        if(ventaDTO.getPrendas()!=null) venta.setPrendas(ventaDTO.getPrendas().stream()
                .map(p -> mapper.map(p, Prenda.class)).collect(Collectors.toSet()));

        Venta savedVenta = ventaRepository.save(venta);

        return mapper.map(savedVenta, VentaDTO.class);
    }

    @Override
    public ResponseDTO deleteVenta(Long number) {
        ventaRepository.findById(number).orElseThrow(()-> new NotFoundException("Venta no encontrada"));
        ventaRepository.deleteById(number);
        return new ResponseDTO("Venta eliminada correctamente");
    }
}
