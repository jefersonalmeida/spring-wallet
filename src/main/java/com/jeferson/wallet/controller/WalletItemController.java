package com.jeferson.wallet.controller;

import com.jeferson.wallet.dto.WalletItemDTO;
import com.jeferson.wallet.entity.UserWallet;
import com.jeferson.wallet.entity.Wallet;
import com.jeferson.wallet.entity.WalletItem;
import com.jeferson.wallet.response.Response;
import com.jeferson.wallet.service.UserWalletService;
import com.jeferson.wallet.service.WalletItemService;
import com.jeferson.wallet.util.Util;
import com.jeferson.wallet.util.enums.TypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "wallet-item")
public class WalletItemController {

    private static final Logger log = LoggerFactory.getLogger(WalletItemController.class);
    @Autowired
    private WalletItemService walletItemService;
    @Autowired
    private UserWalletService userWalletService;

    @PostMapping
    public ResponseEntity<Response<WalletItemDTO>> store(@Valid @RequestBody WalletItemDTO dto, BindingResult result) {

        Response<WalletItemDTO> response = new Response<>();
        if (result.hasErrors()) {
            result.getAllErrors().forEach(e -> response.getErrors().add(e.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        WalletItem object = walletItemService.save(this.convertDTOToEntity(dto));

        response.setData(this.convertEntityToDTO(object));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(value = "{wallet}")
    public ResponseEntity<Response<Page<WalletItemDTO>>> findBetweenDates(
            @PathVariable("wallet") Long wallet,
            @RequestParam("startDate") @DateTimeFormat(pattern = "dd-MM-yyyy") Date startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = "dd-MM-yyyy") Date endDate,
            @RequestParam(name = "page", defaultValue = "0") int page
    ) {

        Response<Page<WalletItemDTO>> response = new Response<>();

        Optional<UserWallet> uw = userWalletService.findByUsersIdAndWalletsId(Util.getAuthenticatedUserId(), wallet);

        if (!uw.isPresent()) {
            response.getErrors().add("Você não tem acesso a essa carteira");
            return ResponseEntity.badRequest().body(response);
        }

        Page<WalletItem> items = walletItemService.findBetweenDates(wallet, startDate, endDate, page);
        Page<WalletItemDTO> dto = items.map(this::convertEntityToDTO);
        response.setData(dto);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping(value = "type/{wallet}")
    public ResponseEntity<Response<List<WalletItemDTO>>> findByWalletAndType(
            @PathVariable("wallet") Long wallet,
            @RequestParam("type") String type
    ) {

        log.info("Buscando por carteira {} e tipo {}", wallet, type);

        Response<List<WalletItemDTO>> response = new Response<>();
        List<WalletItem> list = walletItemService.findByWalletAndType(wallet, TypeEnum.getEnum(type));

        List<WalletItemDTO> dto = new ArrayList<>();
        list.forEach(i -> dto.add(this.convertEntityToDTO(i)));
        response.setData(dto);
        return ResponseEntity.ok().body(response);

    }

    @GetMapping(value = "total/{wallet}")
    public ResponseEntity<Response<BigDecimal>> sumByWalletId(@PathVariable("wallet") Long wallet) {

        Response<BigDecimal> response = new Response<>();
        BigDecimal value = walletItemService.sumByWalletId(wallet);

        response.setData(value == null ? BigDecimal.ZERO : value);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping
    public ResponseEntity<Response<WalletItemDTO>> update(@Valid @RequestBody WalletItemDTO dto, BindingResult result) {
        Response<WalletItemDTO> response = new Response<>();
        Optional<WalletItem> wi = walletItemService.findById(dto.getId());

        if (!wi.isPresent()) {
            result.addError(new ObjectError("WalletItem", "WalletItem não encontrado"));
        } else if (wi.get().getWallet().getId().compareTo(dto.getWallet()) != 0) {
            result.addError(new ObjectError("WalletItemChanged", "Você não pode alterar a carteira"));
        }

        if (result.hasErrors()) {
            result.getAllErrors().forEach(e -> response.getErrors().add(e.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        WalletItem saved = walletItemService.save(this.convertDTOToEntity(dto));

        response.setData(this.convertEntityToDTO(saved));
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping(value = "{walletItemId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Response<String>> destroy(@PathVariable("walletItemId") Long walletItemId) {
        Response<String> response = new Response<>();
        Optional<WalletItem> wi = walletItemService.findById(walletItemId);

        if (!wi.isPresent()) {
            response.getErrors().add("WalletItem de id " + walletItemId + " não encontrada");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        walletItemService.deleteById(walletItemId);
        response.setData("WalletItem de id " + walletItemId + " apagada com sucesso");
        return ResponseEntity.ok().body(response);
    }

    private WalletItem convertDTOToEntity(WalletItemDTO dto) {
        WalletItem wi = new WalletItem();
        wi.setId(dto.getId());
        wi.setDate(dto.getDate());
        wi.setDescription(dto.getDescription());
        wi.setType(TypeEnum.getEnum(dto.getType()));
        wi.setValue(dto.getValue());

        Wallet w = new Wallet();
        w.setId(dto.getWallet());
        wi.setWallet(w);

        return wi;
    }

    private WalletItemDTO convertEntityToDTO(WalletItem wi) {
        WalletItemDTO dto = new WalletItemDTO();
        dto.setId(wi.getId());
        dto.setDate(wi.getDate());
        dto.setDescription(wi.getDescription());
        dto.setType(wi.getType().getValue());
        dto.setValue(wi.getValue());
        dto.setWallet(wi.getWallet().getId());

        return dto;
    }
}
